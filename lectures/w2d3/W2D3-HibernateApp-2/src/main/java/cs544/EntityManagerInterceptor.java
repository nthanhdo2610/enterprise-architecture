package cs544;

import jakarta.persistence.EntityManager;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter(filterName = "OpenEntityManagerInView", urlPatterns = "/*")
public class EntityManagerInterceptor implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        EntityManager em = EntityManagerHelper.getCurrent();
        try {
            em.getTransaction().begin();
            chain.doFilter(req, res);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em != null && em.isOpen()) em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}

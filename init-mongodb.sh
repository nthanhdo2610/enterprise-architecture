#!/bin/bash

# Wait for MongoDB to start
until mongo --host mongodb --eval "print(\"Connection established\")"
do
    echo "Waiting for MongoDB to start..."
    sleep 2
done

# Create the MongoDB user with root privileges
mongo --host mongodb <<EOF
use admin
db.createUser({
  user: 'root',
  pwd: 'root',
  roles: [ { role: 'readWrite', db: 'cs544' } ]
})
EOF


# Run Export
docker-compose run -T -v  $PWD/target/:/target/ -T --no-deps --rm spark-submit bash -c 'spark-submit  --master spark://spark:7077 --conf spark.hadoop.lakefs.api.url=http:/10.5.0.55:8000/api/v1   --conf spark.hadoop.lakefs.api.access_key=${TESTER_ACCESS_KEY_ID}   --conf spark.hadoop.fs.s3a.connection.ssl.enabled=false   --conf spark.hadoop.lakefs.api.secret_key=${TESTER_SECRET_ACCESS_KEY}   --class io.treeverse.clients.Main  ${CLIENT_JAR} test-data s3://nessie-system-testing/export-one-time-test-guy   --branch=main'

# Validate export #TODO add validation

# Run Garbage Collection 
docker-compose run -T -v  $PWD/target/:/target/ -T --no-deps --rm spark-submit bash -c 'spark-submit --master spark://spark:7077 --class io.treeverse.clients.GarbageCollector   -c spark.hadoop.lakefs.api.url=http:/10.5.0.55:8000/api/v1 -c spark.hadoop.lakefs.api.access_key=${TESTER_ACCESS_KEY_ID}    -c spark.hadoop.lakefs.api.secret_key=${TESTER_SECRET_ACCESS_KEY}     -c spark.hadoop.fs.s3a.access.key=${LAKEFS_BLOCKSTORE_S3_CREDENTIALS_ACCESS_KEY_ID}   -c spark.hadoop.fs.s3a.secret.key=${LAKEFS_BLOCKSTORE_S3_CREDENTIALS_SECRET_ACCESS_KEY} ${CLIENT_JAR} test-data us-east-1'


# Validate Garbage Collection #TODO add validation

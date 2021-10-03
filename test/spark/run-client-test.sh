
# Run Export
docker-compose run -v  $PWD/target/:/target/ -T --no-deps --rm spark-submit bash -c 'spark-submit  --master spark://spark:7077 --conf spark.hadoop.lakefs.api.url=http:/10.5.0.55:8000/api/v1   --conf spark.hadoop.lakefs.api.access_key=${TESTER_ACCESS_KEY_ID}   --conf spark.hadoop.fs.s3a.connection.ssl.enabled=false   --conf spark.hadoop.lakefs.api.secret_key=${TESTER_SECRET_ACCESS_KEY}   --class io.treeverse.clients.Main  ${CLIENT_JAR} test-data ${EXPORT_LOCATION}   --branch=main'

# Validate export #TODO add validation
lakectl_out=$(mktemp)
s3_out=$(mktemp)
trap 'rm -f -- $s3_out $lakectl_out' INT TERM EXIT

docker-compose exec -T lakefs lakectl fs ls --recursive --no-color lakefs://test-data/main/ | awk '{print $8}' | sort > ${lakectl_out}

aws s3 ls --recursive ${EXPORT_LOCATION} | awk '{print $4}'| cut -d/ -f 2-  | grep -v EXPORT_ | sort > ${s3_out}

if ! diff ${lakectl_out} ${s3_out}; then
  echo "export location contains and lakefs contain different files"
  exit 1
fi
# Run Garbage Collection 
docker-compose run  -v  $PWD/target/:/target/ -T --no-deps --rm spark-submit bash -c 'spark-submit --master spark://spark:7077 --class io.treeverse.clients.GarbageCollector   -c spark.hadoop.lakefs.api.url=http:/10.5.0.55:8000/api/v1 -c spark.hadoop.lakefs.api.access_key=${TESTER_ACCESS_KEY_ID}    -c spark.hadoop.lakefs.api.secret_key=${TESTER_SECRET_ACCESS_KEY}     -c spark.hadoop.fs.s3a.access.key=${LAKEFS_BLOCKSTORE_S3_CREDENTIALS_ACCESS_KEY_ID}   -c spark.hadoop.fs.s3a.secret.key=${LAKEFS_BLOCKSTORE_S3_CREDENTIALS_SECRET_ACCESS_KEY} ${CLIENT_JAR} test-data us-east-1'


# Validate Garbage Collection #TODO add validation

if ! docker-compose exec lakefs lakectl fs cat lakefs://test-data/697297df0c01d17/commits/commit-ten > /dev/null ; then
  echo expected file commit-ten to exist
  exit 1
fi
if ! docker-compose exec lakefs lakectl fs cat lakefs://test-data/445940036f2135b/commits/commit-nine > /dev/null ; then
  echo expected file commit-nine to exist
  exit 1
fi
if ! docker-compose exec lakefs lakectl fs cat lakefs://test-data/c5d9f3a7d850833/commits/commit-eight > /dev/null ; then
  echo expected file commit-eight to exist
  exit 1
fi
if ! docker-compose exec lakefs lakectl fs cat lakefs://test-data/46c9a9dfcd89c07/commits/commit-six > /dev/null ; then
  echo expected file commit-six to exist
  exit 1
fi
if ! docker-compose exec lakefs lakectl fs cat lakefs://test-data/651a755f7f5b1a4/commits/commit-three > /dev/null ; then
  echo expected file commit-three to exist
  exit 1
fi
if docker-compose exec lakefs lakectl fs cat lakefs://test-data/09d9016a12777fe/commits/commit-four > /dev/null 2>&1 ; then
  echo expected file commit-four to be removed by garbage collection
  exit 1
fi
if docker-compose exec lakefs lakectl fs cat lakefs://test-data/18c9a1fe4695109/commits/commit-two > /dev/null 2>&1 ; then
  echo expected file commit-two to be removed by garbage collection
  exit 1
fi
if docker-compose exec lakefs lakectl fs cat lakefs://test-data/efb14f67a2a3c38/commits/commit-one > /dev/null 2>&1 ; then
  echo expected file commit-one to be removed by garbage collection
  exit 1
fi
if docker-compose exec lakefs lakectl fs cat lakefs://test-data/dede737a85e27f4/commits/commit-five > /dev/null 2>&1 ; then
  echo expected file commit-five to be removed by garbage collection
  exit 1
fi


protoc --java_out=src/main/kotlin --kotlin_out=src/main/kotlin protobuf/wrp-example.proto
pb gen wrp-kt -o=src/main/kotlin \
    --host=pbkit.wrp.example.WrpExampleService \
    --guest=pbkit.wrp.example.WrpExampleService \
    protobuf/wrp-example.proto

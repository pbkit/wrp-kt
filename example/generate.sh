generator=`readlink -f ../generator/build/libs/generator.jar`
protoc --pbandk_out="kotlin_service_gen=$generator|dev.pbkit.wrp.gen.Generator,kotlin_package=dev.pbkit.wrp:src/main/kotlin" protobuf/wrp-example.proto

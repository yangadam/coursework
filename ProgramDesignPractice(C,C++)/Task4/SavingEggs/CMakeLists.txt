cmake_minimum_required(VERSION 3.18)
project(SavingEggs)


# Download automatically, you can also just copy the conan.cmake file
if(NOT EXISTS "${CMAKE_BINARY_DIR}/conan.cmake")
   message(STATUS "Downloading conan.cmake from https://github.com/conan-io/cmake-conan")
   file(DOWNLOAD "https://github.com/conan-io/cmake-conan/raw/v0.15/conan.cmake"
                 "${CMAKE_BINARY_DIR}/conan.cmake" 
                 TLS_VERIFY ON)
endif()

include(${CMAKE_BINARY_DIR}/conan.cmake)

conan_add_remote(NAME bincrafters 
                 INDEX 1
                 URL https://api.bintray.com/conan/bincrafters/public-conan
                 VERIFY_SSL True) 

conan_cmake_run(REQUIRES sfml/2.5.1@bincrafters/stable
                BASIC_SETUP 
                BUILD_TYPE Release
                BUILD missing)

include_directories(${PROJECT_SOURCE_DIR}/include)

aux_source_directory(${PROJECT_SOURCE_DIR}/source DIR_SRCS)
add_executable(${PROJECT_NAME} ${DIR_SRCS})

target_link_libraries(${PROJECT_NAME} ${CONAN_LIBS})

install(DIRECTORY ${PROJECT_SOURCE_DIR}/media
			DESTINATION ${PROJECT_SOURCE_DIR}/build/bin
			PATTERN "CMakeLists.txt" EXCLUDE)

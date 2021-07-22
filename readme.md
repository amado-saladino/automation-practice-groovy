# Sample Gradle Test With Groovy

This project runs tests written in Groovy with Junit 5

## Test Web site

These tests target this [demo web site](http://automationpractice.com/index.php)

## Configuration

It loads the configuration from YAML file `src/main/resources/config.yaml`

## Test Types

REST API tests as well as UI tests could be easily implemented all in Groovy

## jQuery Support

jQuery selector could be easily injected into the page on the fly even if it does not support jQuery, from any child page class, call `injectScriptFile('jquery.js')` method.
It could be then used this way:

```groovy
String placeholder = runScript("return \$('#search_query_top').attr('placeholder')")
```

## Build Docker Image

```shell
docker build -t test .
```

## Run Tests

```shell
docker run --name=test -v `pwd`:/app -v gradle-repo:/root/.gradle/caches/modules-2/files-2.1 -u root test
```

or

```shell
docker run --name=test -v `pwd`:/app -v gradle-repo:/root/.gradle/caches/modules-2/files-2.1 -u root amadosaladino/selenium-gradle
```

## Test Report

```shell
docker run -d --name report -v $PWD/build/reports/tests/test:/usr/share/nginx/html -p 80:80 nginx:1.14
```

## Screenshot Viewer

```shell
docker run -d -v $PWD/screenshots:/Pictures:ro -p 81:80 --name gallery ghcr.io/linuxserver/photoshow
```

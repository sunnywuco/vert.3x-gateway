#!/usr/bin/env bash
### END INIT INFO
PORT=$3
PROFILE=$2
SERVICE_NAME=stone-message
PATH_TO_JAR=/message/stone-message.jar
HEAP_MEMORY=1024m
PERM_MEMORY=128m
DIRECT_MEMORY=512m
OPTS="-Xmx512m  -Dlogging.file=/stone/logs/message/message.log"
JAVA_OPTS="${JAVA_OPTS} -Xms${HEAP_MEMORY} -Xmx${HEAP_MEMORY} -XX:PermSize=${PERM_MEMORY} -XX:MaxPermSize=${PERM_MEMORY}  -Dlogging.file=/message/message.log"
JAVA_OPTS="${JAVA_OPTS} -XX:MaxDirectMemorySize=${DIRECT_MEMORY}"
JAVA_OPTS="${JAVA_OPTS} -XX:+AlwaysPreTouch"
JAVA_OPTS="${JAVA_OPTS} -Dio.netty.allocator.type=pooled"
JAVA_OPTS="${JAVA_OPTS} -Dio.netty.leakDetection.level=PARANOID"
JAVA_OPTS="${JAVA_OPTS} -Dio.netty.leakDetection.maxRecords=20"
JAVA_OPTS="${JAVA_OPTS} -Dio.netty.leakDetection.acquireAndReleaseOnly=true"
JAVA_OPTS="${JAVA_OPTS} -Duser.dir=${SERVICE_NAME} -Dapp.name=$SERVICE_NAME"
echo "start jvm args ${JAVA_OPTS}"
PID_PATH_NAME=/message/stone-message-pid
case $1 in
    start)
        echo "Starting $SERVICE_NAME ..."
        if [ ! -f $PID_PATH_NAME ]; then
                echo "nohup java  -Dspring.profiles.active=$PROFILE $JAVA_OPTS -jar $PATH_TO_JAR $SERVICEOPTS  2>> /dev/null >> /dev/null"
                nohup java  -Dspring.profiles.active=$PROFILE $JAVA_OPTS -jar $PATH_TO_JAR $SERVICEOPTS  2>> /dev/null >> /dev/null &
                        echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is already running ..."
        fi
    ;;
    stop)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stoping ..."
            kill $PID;
            echo "$SERVICE_NAME stopped ..."
            rm $PID_PATH_NAME
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
    restart)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stopping ...";
            kill $PID;
            echo "$SERVICE_NAME stopped ...";
            rm $PID_PATH_NAME
            echo "$SERVICE_NAME starting ..."
            nohup java  -Dspring.profiles.active=$PROFILE -jar $PATH_TO_JAR $SERVICEOPTS  2>> /dev/null >> /dev/null &
                        echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
esac
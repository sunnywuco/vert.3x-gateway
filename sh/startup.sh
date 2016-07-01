#!/bin/sh

### BEGIN INIT INFO
# Provides:          Young
# Required-Start:    $local_fs $remote_fs $network
# Required-Stop:     $local_fs $remote_fs $network
# Should-Start:      $named
# Should-Stop:       $named
# Default-Start:     2 3 4 5
# Default-Stop:      0 1 6
# Short-Description: Start Tomcat YOUR_APP_NAME.
# Description:       Start the Tomcat YOUR_APP_NAME servlet engine.
### END INIT INFO

HEAP_MEMORY=1024m
PERM_MEMORY=128m
DIRECT_MEMORY=512m
SERVICE_NAME=stone-message
JAVA_OPTS="${JAVA_OPTS} -Xms${HEAP_MEMORY} -Xmx${HEAP_MEMORY} -XX:PermSize=${PERM_MEMORY} -XX:MaxPermSize=${PERM_MEMORY}  -Dlogging.file=/message/message.log"
JAVA_OPTS="${JAVA_OPTS} -XX:MaxDirectMemorySize=${DIRECT_MEMORY}"
JAVA_OPTS="${JAVA_OPTS} -XX:+AlwaysPreTouch"
JAVA_OPTS="${JAVA_OPTS} -Dio.netty.allocator.type=pooled"
JAVA_OPTS="${JAVA_OPTS} -Dio.netty.leakDetection.level=PARANOID"
JAVA_OPTS="${JAVA_OPTS} -Dio.netty.leakDetection.maxRecords=20"
JAVA_OPTS="${JAVA_OPTS} -Dio.netty.leakDetection.acquireAndReleaseOnly=true"
JAVA_OPTS="${JAVA_OPTS} -Duser.dir=${SERVICE_NAME} -Dapp.name=$SERVICE_NAME"
echo "start jvm args ${JAVA_OPTS}"
PATH_TO_JAR=/message/stone-message.jar
OPTS="-Xmx512m
PID_PATH_NAME=/message/stone-message-pid
case $1 in
    start)
        echo "Starting $SERVICE_NAME ..."
        if [ ! -f $PID_PATH_NAME ]; then
            nohup java -jar -Dspring.profiles.active=dev $JAVA_OPTS $PATH_TO_JAR /tmp 2>> /dev/null >> /dev/null &
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
            echo "$SERVICE_NAME stopping ..."
            kill $PID;
            echo "$SERVICE_NAME stopped ..."
            rm $PID_PATH_NAME
            echo "$SERVICE_NAME starting ..."
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
esac

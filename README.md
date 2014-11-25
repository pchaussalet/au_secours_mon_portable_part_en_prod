
Steps
-----

Initialization
app:base
    cd 1/app/base
    docker build -t bbl_pch/app:base .

mysql
    cd mysqld
    docker build -t bbl_pch/mysqld .

nginx
    cd nginx
    docker build -t bbl_pch/nginx .

Dev

    cd 1/app
    ./run_app.sh
    # DEMO
    cd ..
    ./clean.sh
    cd ..

UAT

    cd 2
    ./start_platform.sh
    # DEMO
    ./stop_platform.sh
    cd ..

Perf

    cd 3
    ./start_platform.sh
    docker logs -f appA # in a new terminal
    docker logs -f appB # in a new terminal
    ./launch_test.sh
    ./stop_platform.sh
    cd ..

Pre Prod

    cd 4
    fig up
    fig scale app=4
    fig scale app=2
    fig scale app=1
    fig stop
    fig rm

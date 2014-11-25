for service in mysqld nginx; do cd $service; docker build -t bbl_pch/${service} .; cd -; done

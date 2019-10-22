while true
do
bash ./backend/sq_connect.sh | tee ./backend/result.txt
sleep 1s
sudo java -jar ./backend/tsqv_backend.jar /var/www/html/index.php /home/pi/tsquickviewer/ressources/standard.css /home/pi/tsquickviewer/html /home/pi/tsquickviewer/backend

sleep 5s
done

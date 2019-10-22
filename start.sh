while true
do
bash ./backend/sq_connect.sh | tee ./backend/result.txt
sleep 1s
sudo java -jar ./backend/tsq_verarbeitung.jar /var/www/html/index.php /home/pi/tsquickviewer/ressources/standard.css /home/pi/tsquickviewer/html

sleep 5s
done
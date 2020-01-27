kotlinc src/io/posidon/potassium -include-runtime -d potassium.jar
if [ $? -eq 0 ]; then
  chmod +x potassium.jar
  java -jar potassium.jar
fi
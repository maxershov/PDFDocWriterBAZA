# PDF Document Writer
PDFDocWriterBAZA

I created this for my work. Tired of typing in MSWord.

This program reads the PDF doc and writes the info(first and last name, date of birth and date for pass) from user input in GUI and creates new PDF and prints it. PDF box doesn't have default russian font, so i added `AG_Helvetica.ttf` 

You must download the font `AG_Helvetica.ttf` in the `/bin` folder. For printing, you must enter the name of your printer in `/src/ParserPDF/PrintPDF.java` file. 
![Alt text](https://github.com/maxershov/PDFDocWriterBAZA/blob/master/screenshot0.png?raw=true)

and uncomment this lines in `/src/ParserPDF/PDFWriter.java` file.
![Alt text](https://github.com/maxershov/PDFDocWriterBAZA/blob/master/screenshot2.png?raw=true)

For use in runnableJar, you need to transfer `pass0.pdf` and `AG_Helvetica.ttf` to the `/resource` folder.

+Added shortcut for today and tomorrow dates in GUI.

GUI:

![Alt text](https://github.com/maxershov/PDFDocWriterBAZA/blob/master/screenshot.png?raw=true)

Output on empty PDF file will look like this:

![Alt text](https://github.com/maxershov/PDFDocWriterBAZA/blob/master/screenshot1.png?raw=true)



Я создал эту программу для работы. Устал печатать в MSWord.

Программа открывает PDF документ, вводит в него необходимую информацию (ФИО, дату рождения, дату пропуска), создает новый PDF документ и печает его. 

Для работы программы необходимо скачать шрифт `AG_Helvetica.ttf` в папку `/bin` 
Для быстрой печати необходимо ввести в Java файл `/src/ParserPDF/PrintPDF.java` имя вашего принтера. 
![Alt text](https://github.com/maxershov/PDFDocWriterBAZA/blob/master/screenshot0.png?raw=true)


и убрать комментарий из `/src/ParserPDF/PDFWriter.java` в этих строках.
![Alt text](https://github.com/maxershov/PDFDocWriterBAZA/blob/master/screenshot2.png?raw=true)


Для использования в runnableJar необходимо перенести `pass0.pdf` и `AG_Helvetica.ttf` в папку `/resource`

На выходе получаем это (из пустого документа):

![Alt text](https://github.com/maxershov/PDFDocWriterBAZA/blob/master/screenshot1.png?raw=true)





# PDF Document Writer
PDFDocWriterBAZA

I created this for my work. Tired of typing in MSWord.

Use `commons-logging-1.1.2`, `fontbox-2.0.16`, `pdfbox-2.0.16` lib.

This program reads the PDF doc and writes the info(first and last name, date of birth and date for pass) from user input in GUI and creates new PDF and prints it. PDF box doesn't have default russian font, so i added `AG_Helvetica.ttf` 

You must download the font `AG_Helvetica.ttf` in the `/resource` folder. For printing, you must enter the name of your printer in `/resource/properties.xml` file in `<"printerName">_______<`. 
![Alt text](https://github.com/maxershov/PDFDocWriterBAZA/blob/master/screenshot0.png?raw=true)

or leave `"Empty"` if you want to print later.

+Added shortcut for today and tomorrow dates in GUI.

GUI:

![Alt text](https://github.com/maxershov/PDFDocWriterBAZA/blob/master/screenshot.png?raw=true)

Output on empty PDF file will look like this:

![Alt text](https://github.com/maxershov/PDFDocWriterBAZA/blob/master/screenshot1.png?raw=true)



Я создал эту программу для работы. Устал печатать в MSWord.

Использовал библиотеки `commons-logging-1.1.2`, `fontbox-2.0.16`, `pdfbox-2.0.16`.

Программа открывает PDF документ, вводит в него необходимую информацию (ФИО, дату рождения, дату пропуска), создает новый PDF документ и печает его. 

Для работы программы необходимо скачать шрифт `AG_Helvetica.ttf` в папку `/resource` 
Для быстрой печати необходимо ввести в Java файл `/resource/properties.xml` имя вашего принтера в `<printerName>`. 
![Alt text](https://github.com/maxershov/PDFDocWriterBAZA/blob/master/screenshot0.png?raw=true)

Или оставьте `"Empty"` если хотите напечатать файл потом.

На выходе получаем это (из пустого документа):

![Alt text](https://github.com/maxershov/PDFDocWriterBAZA/blob/master/screenshot1.png?raw=true)





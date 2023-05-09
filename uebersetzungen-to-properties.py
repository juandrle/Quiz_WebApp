import os

with open("uebersetzungen.csv") as my_file:
    s = my_file.read()
lang = 1

lines = s.split('\n')
languages = lines[0].split(';')
for header in languages:
    if lang == len(languages):
        break
    if os.path.exists(f'src/main/resources/messages_{languages[lang]}.properties'):
        os.remove(f'src/main/resources/messages_{languages[lang]}.properties')
    temp = open(f'src/main/resources/messages_{languages[lang]}.properties', 'w')
    i = 0
    for line in s.split('\n'):
        for part in line.split(';'):
            if i == len(languages):
                i = 0
            if i == 0 and part not in languages and part != '':
                temp.write(part + '=')
            if i == lang and part not in languages:
                temp.write(part + '\n')
            i += 1    
    lang += 1
    temp.close()
temp = open('src/main/resources/messages_en.properties', 'r')
if os.path.exists('src/main/resources/messages.properties'):
    os.remove('src/main/resources/messages.properties')
default = open('src/main/resources/messages.properties', 'wt')
line = temp.read()
default.write(str(line))
temp.close()
default.close()    
        
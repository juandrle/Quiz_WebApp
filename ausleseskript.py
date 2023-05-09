with open("uebersetzungen.csv") as my_file:
    s = my_file.read()
lang = 1

lines = s.split('\n')
languages = lines[0].split(';')
for header in languages:
    if lang == len(languages):
        break
    temp = open(f'src/main/resources/message_{languages[lang]}.properties', 'w')
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
    
        
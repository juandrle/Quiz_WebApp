
CSV = "uebersetzungen.csv"
PATH = "src/main/resources/"
FILENAME = PATH + "messages_{language}.properties"
DEFAULT_FILENAME = PATH + "messages.properties"

# Change default language to your liking
DEFAULT_LANGUAGE = "de"

files = []

with open(CSV) as t:
    lines = t.readlines()
    firstline = lines[0].split(";")

    for lang in firstline[1:]:
        lang = lang.rstrip()
        files.append((open(FILENAME.format(language=lang), 'w'), lang))

    defaultfile = open(DEFAULT_FILENAME, "w")

    for line in lines[1:]:
        line = line.split(";")

        property_name = line[0]
        translations = line[1:]

        for ftup, transl in zip(files, translations):
            f, lang = ftup
            transl = transl.rstrip()

            if (lang == DEFAULT_LANGUAGE):
                defaultfile.write("{prop}={transl}\n".format(
                    prop=property_name, transl=transl))

            f.write("{prop}={transl}\n".format(
                prop=property_name, transl=transl))

for f, l in files:
    f.close()

defaultfile.close()

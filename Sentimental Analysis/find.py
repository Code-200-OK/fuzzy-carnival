from HTMLParser import HTMLParser  
import urllib2
from BeautifulSoup import BeautifulSoup
from BeautifulSoup import BeautifulStoneSoup
import re

URL = "http://lyrics.wikia.com/wiki/"
LOC = "/home/jatsakthi/DATA/HAPPY/"

def downloadLyrics(artist, song):
    wiki = URL + artist.replace(" ","_") + ":" + song.replace(" ","_")
    print "searching for:"+wiki
    page = urllib2.urlopen(wiki)
    soup = BeautifulSoup(page)
    lyricBox = soup.find('div',{"class" : 'lyricbox'})
    song = lyricBox.text
    title = soup.title.text
    text = unicode(BeautifulStoneSoup(song, convertEntities=BeautifulStoneSoup.ALL_ENTITIES))
    newtext = ""
    for word in text.split(" "):
        if len(re.findall('[A-Z]', word)) > 0:
            for letter in list(word):
                if(letter.isupper()):
                    newtext += ".\n"
                newtext += letter
        else:
            newtext += word
        newtext += " "   
    return title,newtext

def writeLyrics(title, song):
    song = song.encode('utf-8')
    file = open(LOC+title+".txt","w")
    file.write(song)
    file.close()

''' 
wiki = "http://lyrics.wikia.com/wiki/James_Brown:I_Feel_Good"
page = urllib2.urlopen(wiki)
soup = BeautifulSoup(page)
lyricBox = soup.find('div',{"class" : 'lyricbox'})
song = lyricBox.text
title = soup.title.text
text = unicode(BeautifulStoneSoup(song, convertEntities=BeautifulStoneSoup.ALL_ENTITIES))
newtext = ""
for word in text.split(" "):
    if len(re.findall('[A-Z]', word)) > 0:
        for letter in list(word):
            if(letter.isupper()):
                newtext += ".\n"
            newtext += letter
    else:
        newtext += word
    newtext += " " 
print title
print newtext
'''
error = []
found = 0;
lineNumber = 1
with open(LOC+"List") as f:
    lines = f.readlines()
for line in lines:
    content = line.strip().split(",")
    print str(lineNumber)+" Searching for "+ content[0] +"'s "+content[1]
    try:
        title,song = downloadLyrics(content[0],content[1])
        print "Lyrics Found. Writing.."
        writeLyrics(title, song)
        found += 1
        lineNumber += 1
    except:
        error.append(content[0]+":"+content[1]);
        print "Error"
        lineNumber += 1
print "Found  total: "+ str(found)
print "Couldnt' Find "+ str(len(error))
print error

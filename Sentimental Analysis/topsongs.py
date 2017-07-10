from HTMLParser import HTMLParser  
import urllib2
from BeautifulSoup import BeautifulSoup
from BeautifulSoup import BeautifulStoneSoup
import re

URL = "https://www.upvenue.com/article/1864-top-65-songs-that-will-make-you-happy-instantly.html/10"
LOC = "/home/jatsakthi/SML/classification/Document Classification/Songs/Dataset/Downloaded/Happy/"

def downloadLyrics():
    wiki = URL
    page = urllib2.urlopen(wiki)
    soup = BeautifulSoup(page)
    lyricBox = soup.find('div',{"class" : 'pageBlocks'})
    ans = []
    for links in lyricBox.findAll("h2"):
        span = links.find('span').text
        text = links.text.replace(span,'')
        text = text.strip()
        text = text.replace(" - ",",")
        ans.append(text)
    return ans
        
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
file = open(LOC+"List","a")
for entry in downloadLyrics():
    print "writing " + entry
    try:
        file.write(entry)
        file.write("\n")
    except:
        print " Error" 
file.close
    


from HTMLParser import HTMLParser  
import urllib2
from BeautifulSoup import BeautifulSoup
from BeautifulSoup import BeautifulStoneSoup
import re

URL = "http://www.nme.com/list/50-songs-about-depression-1109"
LOC = "/home/jatsakthi/dlist"

def downloadLyrics():
    wiki = URL
    page = urllib2.urlopen(wiki)
    soup = BeautifulSoup(page)
    lyricBox = soup.find('div',{"itemprop" :'about'})
    links = lyricBox.findAll('h2')
    #lyricBox.findAll('h2')[2].find(text=True,recursive=False).encode('ascii', 'ignore').decode('ascii').strip()
    ans = []
    for link in links:
        content = link.find(text=True,recursive=False).encode('utf-8').strip().split('\xe2\x80\x93')
        
        #content = link.find(text=True,recursive=False)
        #content = content.encode('ascii', 'ignore').decode('ascii').strip()
        #content = content.replace("\n",'')
        #content = content.replace("&amp;",'&')
        #content = content.replace("&nbsp;",'')
        
        #text = content.split("-");
        try:
            print content[1].decode('utf-8').encode('ascii', 'replace').decode('ascii').strip()
            print content[0].decode('utf-8').encode('ascii', 'replace').decode('ascii').strip()
            ans.append(content[1].decode('utf-8').encode('ascii', 'ignore').decode('ascii').strip() + ","+content[0].decode('utf-8').encode('ascii', 'ignore').decode('ascii').strip());
        except:
            print "Error:"+ str(len(content))
    return  ans;
'''  
    for links,la in body.findAll('td',{"class" : 'leftCell boldCell tableCell firstCell '}), body.findAll('td',{"class" : 'centerCell unboldCell tableCell  '}):
        
        text = links.find("h2").text
        text = text.replace("&quot;","")
        content = text.split(" by ")
        if(len(content)==2):
            text = content[0]
            result = ''
            found = True
            for letters in list(text):
                if(not found):
                    result += letters
                elif(not letters.isdigit() and (letters != '.')):
                    found = False;
                    result += letters
                    
            result = result.strip()            
            ans.append(content[1]+","+result)
            ''' 

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
    print entry
    try:
        file.write(entry)
        file.write("\n")
    except:
        print " Error"
file.close
    


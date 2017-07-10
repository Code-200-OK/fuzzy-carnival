import re

if __name__ == '__main__':
    LOC = "/home/jatsakthi/DATA/"
    with open(LOC+"HL10") as f:
        lines = f.readlines()
    file = open(LOC+"HL10List","w")
    for line in lines:
        '''
        line = line.strip()
        print line
        song = re.findall('"([^"]*)"',line)[0].strip()
        line = re.sub('"([^"]*)"',"",line)
        author = line.split("by ")[1].strip()
        '''
        if len(line) >1:
            print line
            
            #line = re.sub('\"([^*]*)\"',"",line)
            line = line.strip()
            print line
            line = re.sub('\d\d',"",line)
            line = line.strip()
            print line
            song = re.findall('"([^"]*)"',line)[0]
            print line.split(" - ")
            author = line.split(" - ")[0].strip()
        
        #line = re.sub('"([^"]*)"',"",line)
        #author = line.strip()
        
            file.write(author+","+song+"\n")
    file.close();
    
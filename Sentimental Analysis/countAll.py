import os


def read_files(location):
    print "GOT: "+location
    for root, dir_names, file_names in os.walk(location):
        if root not in (os.path.join(location,"Edited"),os.path.join(location,"List")):
            print "Current root"+root
            print "Current FileNames"+ str(len(file_names));      
            for file_name in file_names:
                if file_name not  in ignoreFile:
                    if tt.has_key(file_name):
                        '''
                        if(root == "/home/jatsakthi/SML/Project/Data/Happy"):
                            os.remove(os.path.join(root,file_name));
                            print "Removed.."+file_name
                        '''
                        print root.split("/")[6] + " vs " + tt[file_name];
                    else:
                        tt[file_name] = root.split("/")[6]
                        
        
                
if __name__=='__main__':
    LOC = "/home/jatsakthi/SML/Project/Data"
    #ignoreDir = ["/home/jatsakthi/SML/classification/Document Classification/Songs/Dataset/Downloaded/Edited","/home/jatsakthi/SML/classification/Document Classification/Songs/Dataset/Downloaded/List"]
    ignoreFile = ["List","oList"]
    tt = {}
   
    read_files(LOC)
    print len(tt)
from pandas import DataFrame
import pandas as pd
import numpy
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.cross_validation import KFold
from sklearn.metrics import confusion_matrix, f1_score
from sklearn.svm import SVC
import os
import re
import nltk
from nltk.stem import PorterStemmer
from sklearn.svm import LinearSVC

def writeLyrics(title, song):
    song = song.encode('utf-8')
    file = open(title+".txt","w")
    file.write(song)
    file.close()

def build_data_frame(path,stopWords):
    df = pd.read_csv(path,usecols=['file','lyrics','mood'])
    rows = []
    for row in df.values:
        text = row[1]
        mood = row[2]
        text = processText(text,stopWords)
        #writeLyrics("/home/jatsakthi/SML/classification/Document Classification/Songs/Dataset/Downloaded/Edited/"+row[0], text)
        rows.append({'lyrics':text, 'mood':mood})
    dataframe =  DataFrame(rows)
    return dataframe

def create_tfidf_training_data(corpus):
    vectorizer = TfidfVectorizer(min_df=1)
    X = vectorizer.fit_transform(corpus)
    return X

def train_svm(data):
    x_data = []
    y_data = []
    for X,Y in data:
        x_data.append(X)
        y_data.append(Y)
    svm = SVC(C=1000000.0, gamma='auto', kernel='rbf')
    svm.fit(x_data, y_data)
    return svm

def read_files(location):
    for root, dir_names, file_names in os.walk(location):
        for file_name in file_names:
            file_path = os.path.join(root, file_name)
            if os.path.isfile(file_path):
                f = open(file_path, "rt")
                lines = []
                for line in f:
                    lines.append(line)
                f.close()
                content = ''.join(lines)
                yield file_path, content

def add_to_trainingData(path, classification):
    rows = []
    index = []
    for file_name, text in read_files(path):
        Ptext = processText(text,stopWords)
        rows.append({'lyrics': Ptext, 'mood': classification})
        index.append(file_name)
    
    data_frame = DataFrame(rows)
    return data_frame

def getStopWordList(stopWordListFileName):
    #read the stopwords file and build a list
    stopWords = []
    
    fp = open(stopWordListFileName, 'r')
    line = fp.readline()
    while line:
        word = line.strip()
        stopWords.append(word)
        line = fp.readline()
    fp.close()
    return stopWords

def processLine(line,stopWords):
    
    for c in removeList:
        line = line.replace(c,'')
    newline = ""
    for word in line.split():
        newline += processWord(word,stopWords)+" "
    return newline

def replaceTwoOrMore(s):
    #look for 2 or more repetitions of character and replace with the character itself
    pattern = re.compile(r"(.)\1{1,}", re.DOTALL)
    return pattern.sub(r"\1\1", s)

def processWord(word,stopWords):
    word = word.lower()
    
    #word.replace_all('')
    
    word = word.strip('\'#"?,.!:()')
    if len(word)<3 or (word in stopWords)==True:
        return ''
    else:
        try:
            word = replaceTwoOrMore(word)
            word = ps.stem(word)
            
            return word;
        except:
            return word;
    
def processText(text,stopWords):
    # process the tweets

    #Convert to lower case
    #print text
    text = text.decode('ascii', 'ignore')
    text = text.lower()
    processedText = ""
    for line in text.split("\n"):
        processedText += processLine(line, stopWords) +"\n"
    #Remove additional white spaces
    processedText = re.sub('[\s]+', ' ', processedText)
    #trim
    processedText = processedText.strip('\'"')
    return processedText

def getFeatureVector(text):
    featureVector = []
    words = text.split();
    featureVector.extend(words);
    return featureVector

def extract_features(text):
    text_words = set(text)
    features = []
    for word in featureList:
        features.append((word in text_words))
    return features


if __name__ == "__main__":
    HAPPY = "happy"
    SAD = "sad"
    ps = PorterStemmer();
    removeList = ['?','(',')','/','*','-','#',':','[',']','{','}','&']
    trainLocation = "/home/jatsakthi/SML/classification/Document Classification/Songs/Dataset/train_lyrics_1000.csv"
    testLocation = "/home/jatsakthi/SML/classification/Document Classification/Songs/Dataset/valid_lyrics_200.csv"
    LOC = "/home/jatsakthi/SML/classification/Document Classification/Songs/Dataset/Downloaded/Happy/"
    LOC1 = "/home/jatsakthi/SML/classification/Document Classification/Songs/Dataset/Downloaded/Sad/"
    LOC2 = "/home/jatsakthi/SML/classification/Document Classification/Songs/Dataset/Downloaded/Break Up/"
   
    stopWords = getStopWordList(LOC+'stopwords.txt')
    data = DataFrame({'lyrics': [], 'mood': []})
    data = data.append(build_data_frame(trainLocation,stopWords))
    nt = add_to_trainingData(LOC,HAPPY);
    data = data.append(nt)
    nt = add_to_trainingData(LOC1,SAD);
    data = data.append(nt)
    nt = add_to_trainingData(LOC2,SAD);
    data = data.append(nt)
    
    mydata = []
    featureList = []
    for entry in data.values:
        text = entry[0];
        sentiment = entry[1]
        processedText = processText(text,stopWords)
        featureVector = getFeatureVector(processedText);
        featureList.extend(featureVector)
        mydata.append((featureVector, sentiment));
    
    featureList = list(set(featureList))
    print featureList
    training_set = nltk.classify.util.apply_features(extract_features, mydata)
    svm = train_svm(training_set)
    print "System trained.."
    data = build_data_frame(testLocation,stopWords)
    
    testX = []
    textY = []
    for entry in data.values:
        text = entry[0];
        sentiment = entry[1]
        processedText = processText(text,stopWords)
        featureVector = getFeatureVector(processedText);
        testX.append(extract_features(featureVector))
        textY.append(sentiment)
    
    score = svm.score(testX, textY)
    pred = svm.predict(testX)
    
    print('Total emails classified:', len(textY))
    print('Score:', score)
    print('Confusion matrix:')
    print(confusion_matrix(textY, pred))
    
    
    
    #classifier = nltk.classify.SklearnClassifier(LinearSVC())
    #classifier.train(training_set)
    
        
    
    
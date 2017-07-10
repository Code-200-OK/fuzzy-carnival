# -*- coding: utf-8 -*- 
from pandas import DataFrame
import pandas as pd
import numpy
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.cross_validation import KFold
from sklearn.metrics import confusion_matrix, f1_score
from sklearn.svm import SVC
import os
import re
from nltk.stem import PorterStemmer

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
        writeLyrics("/home/jatsakthi/SML/classification/Document Classification/Songs/Dataset/Downloaded/Edited/"+row[0], text)
        rows.append({'lyrics':text, 'mood':mood})
    dataframe =  DataFrame(rows)
    return dataframe

def create_tfidf_training_data(corpus):
    vectorizer = TfidfVectorizer(min_df=1)
    X = vectorizer.fit_transform(corpus)
    return X

def train_svm(X, y):
    svm = SVC(C=1000000.0, gamma='auto', kernel='rbf')
    svm.fit(X, y)
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

def add_to_trainingData(path, classification,stopWords):
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
   
    newline = ""
    for word in line.split():
        newline += processWord(word,stopWords)+" "
    return newline
    
def processWord(word,stopWords):
    word = word.lower()
    word = word.strip('\'"?,.!')
    if len(word)<3 or (word in stopWords)==True:
        return ''
    else:
        try:
            word = ps.stem(word)
            return word;
        except:
            return word;
    
def processText(text,stopWords):
    # process the tweets

    #Convert to lower case
    text = text.decode('utf-8')
    text = text.lower()
    processedText = ""
    for line in text.split("\n"):
        processedText += processLine(line, stopWords) +"\n"
    #Remove additional white spaces
    processedText = re.sub('[\s]+', ' ', processedText)
    #Replace #word with word
    processedText = re.sub(r'#([^\s]+)', r'\1', processedText)
    #trim
    processedText = processedText.strip('\'"')
    return processedText


if __name__ == "__main__":
    HAPPY = "happy"
    SAD = "sad"
    ps = PorterStemmer();
    trainLocation = "/home/jatsakthi/SML/classification/Document Classification/Songs/Dataset/train_lyrics_1000.csv"
    testLocation = "/home/jatsakthi/SML/classification/Document Classification/Songs/Dataset/valid_lyrics_200.csv"
    LOC = "/home/jatsakthi/SML/classification/Document Classification/Songs/Dataset/Downloaded/Happy/"
   
    stopWords = getStopWordList(LOC+'stopwords.txt')
    data = DataFrame({'lyrics': [], 'mood': []})
    data = data.append(build_data_frame(trainLocation,stopWords))
    
    #data = data.reindex(numpy.random.permutation(data.index))
    nt = add_to_trainingData(LOC,HAPPY,stopWords);
    data = data.append(nt)

    tot = create_tfidf_training_data(data['lyrics'].values)
    
    k_fold = KFold(n=len(data), n_folds=3)
    scores = []
    confusion = numpy.array([[0, 0], [0, 0]])
    for train_indices, test_indices in k_fold:
        train_text = tot[train_indices]
        train_y = data.iloc[train_indices]['mood'].values
    
        test_text = tot[test_indices]
        test_y = data.iloc[test_indices]['mood'].values
        
        svm = train_svm(train_text, train_y)
        
        score = svm.score(test_text, test_y)
        pred = svm.predict(test_text)
        confusion += confusion_matrix(test_y, pred)
        scores.append(score)
        print score

    print('Total emails classified:', len(data))
    print('Score:', sum(scores)/len(scores))
    print('Confusion matrix:')
    print(confusion)
    
    
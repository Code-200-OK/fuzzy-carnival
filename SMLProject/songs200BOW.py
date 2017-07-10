from pandas import DataFrame
import pandas as pd
import numpy
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.cross_validation import KFold
from sklearn.metrics import confusion_matrix
from sklearn.svm import SVC
from sklearn.cross_validation import train_test_split
from sklearn.feature_extraction.text import CountVectorizer
import os


def build_data_frame(path):
    df = pd.read_csv(path,usecols=['file','lyrics','mood'],index_col='file')
    return df
    
def create_bow_training_data(corpus,test_corpus):
    
    count_vectorizer = CountVectorizer()
    X = count_vectorizer.fit_transform(corpus)
    print X.shape
    return X, count_vectorizer.transform(test_corpus)

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

def add_to_trainingData(path, classification):
    rows = []
    index = []
    for file_name, text in read_files(path):
        rows.append({'lyrics': text, 'mood': classification})
        index.append(file_name)
    
    data_frame = DataFrame(rows)
    return data_frame

if __name__ == "__main__":
    HAPPY = "happy"
    SAD = "sad"
    
    trainLocation = "/home/jatsakthi/SML/classification/Document Classification/Songs/Dataset/train_lyrics_1000.csv"
    testLocation = "/home/jatsakthi/SML/classification/Document Classification/Songs/Dataset/valid_lyrics_200.csv"
    LOC = "/home/jatsakthi/SML/classification/Document Classification/Songs/Dataset/Downloaded/Happy/"
    
    training_data = build_data_frame(trainLocation)
    # need to append from  new location as well
    nt = add_to_trainingData(LOC,HAPPY);
    training_data = training_data.append(nt)
    
    test_data = build_data_frame(testLocation)
    training_data = training_data.reindex(numpy.random.permutation(training_data.index))
    test_data = test_data.reindex(numpy.random.permutation(test_data.index))
    
    tot,tot_test = create_bow_training_data(training_data['lyrics'].values,test_data['lyrics'].values)
    #tot_test = create_tfidf_training_data(test_data['lyrics'].values)
    
    # Create and train the Support Vector Machi
    X_train = tot
    X_test = tot_test
    Y_train = training_data['mood'].values
    Y_test = test_data['mood'].values
    
    svm = train_svm(X_train, Y_train)
    
    # Make an array of predictions on the test set
    score = svm.score(X_test, Y_test)
    pred = svm.predict(X_test)
    
    print('Total emails classified:', len(Y_test))
    print('Score:', score)
    print('Confusion matrix:')
    print(confusion_matrix(Y_test, pred))
    
    
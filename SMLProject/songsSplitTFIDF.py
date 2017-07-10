from pandas import DataFrame
import pandas as pd
import numpy
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.cross_validation import KFold
from sklearn.metrics import confusion_matrix
from sklearn.svm import SVC
from sklearn.cross_validation import train_test_split


def build_data_frame(path):
    df = pd.read_csv(path,usecols=['file','lyrics','mood'],index_col='file')
    return df

def create_tfidf_training_data(corpus):
    vectorizer = TfidfVectorizer(min_df=1)
    X = vectorizer.fit_transform(corpus)
    return X

def train_svm(X, y):
    svm = SVC(C=10000.0, gamma='auto', kernel='rbf')
    svm.fit(X, y)
    return svm

if __name__ == "__main__":
    HAPPY = 1
    SAD = 0
    
    trainLocation = "/home/jatsakthi/SML/classification/Document Classification/Songs/Dataset/train_lyrics_1000.csv"
    testLocation = "/home/jatsakthi/SML/classification/Document Classification/Songs/Dataset/valid_lyrics_200.csv"
    
    training_data = build_data_frame(trainLocation)
    test_data = build_data_frame(testLocation)
    training_data = training_data.reindex(numpy.random.permutation(training_data.index))
    test_data = test_data.reindex(numpy.random.permutation(test_data.index))
    
    tot = create_tfidf_training_data(training_data['lyrics'].values)
    
    X_train, X_test, y_train, y_test = train_test_split(
  tot, training_data['mood'].values, test_size=0.2, random_state=42
)
    # Create and train the Support Vector Machine
    svm = train_svm(X_train, y_train)
    
    # Make an array of predictions on the test set
    score = svm.score(X_test, y_test)
    pred = svm.predict(X_test)
    
    print('Total emails classified:', len(y_test))
    print('Score:', score)
    print('Confusion matrix:')
    print(confusion_matrix(y_test, pred))
    
    
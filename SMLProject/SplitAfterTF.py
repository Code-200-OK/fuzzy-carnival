from sklearn.feature_extraction.text import CountVectorizer
from nltk.stem.snowball import SnowballStemmer
from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.svm import SVC
from sklearn.metrics import confusion_matrix
from sklearn.cross_validation import train_test_split
from sklearn.model_selection import GridSearchCV
import os
import re


vectorizer = CountVectorizer(input='filename', ngram_range=(1,1),stop_words='english',token_pattern="(?u)\\b[A-Za-z][A-Za-z][A-Za-z]+\\b")
#print vectorizer
analyzer = vectorizer.build_analyzer()
stemmer = SnowballStemmer("english")

def stemmed_words(doc):
    return (stemmer.stem(replaceTwoOrMore(w)) for w in analyzer(doc))

def readFileNames(locations):
    fileNames = []
    labels = []
    for location in locations:
        for root, dir_names, file_names in os.walk(LOC+location):
            for fileName in file_names:
                fileNames.append(os.path.join(root,fileName))
                if trainlocations[0] in location:
                    labels.append(1)
                elif trainlocations[1] in location:
                    labels.append(-1)
                elif 'Neutral' in location:
                    labels.append(0)    
    return fileNames,labels

def replaceTwoOrMore(s):
    #look for 2 or more repetitions of character and replace with the character itself
    pattern = re.compile(r"(.)\1{1,}", re.DOTALL)
    return pattern.sub(r"\1\1", s)

if __name__=='__main__':
    print "Training the system..."
    HappyLocationList = ['Happy']
    LOC = "/home/jatsakthi/SML/Project/Data/"
    trainlocations = ["Happy","Sad","Neutral"]

    FilesList,Labels = readFileNames(trainlocations)
    
    # ###########        CONVERT IT INTO BOW    ###################
    stem_vectorizer = CountVectorizer(analyzer=stemmed_words)
    #print stem_vectorizer
    
    train_tfidf = stem_vectorizer.fit_transform(FilesList)
    #print train_tfidf.shape
    
    ##################################################################
    
    #print stem_vectorizer.get_feature_names()
    #print stem_vectorizer.vocabulary_.get(u'have')
    '''
    # ###########        CONVERT IT INTO TF-IDF    ###################
    
    tfidf_transformer = TfidfTransformer()
    train_tfidf = tfidf_transformer.fit_transform(train_tfidf)
    
    print train_tfidf.shape
    
    ##################################################################
    '''
    
    X_train_tfidf, Y_train_tfidf, trainLabels, testLabels = train_test_split(
    train_tfidf, Labels, test_size=0.2, random_state=0)
    
    # Set the parameters by cross-validation
    tuned_parameters = [{'kernel': ['rbf'], 'gamma': [1e-3, 1e-4],
                         'C': [1, 10, 100, 1000]},
                        {'kernel': ['linear'], 'C': [1, 10, 100, 1000]}]
    
    clf = GridSearchCV(SVC(C=1), tuned_parameters, cv=5)
    clf.fit(X_train_tfidf, trainLabels)
    y_pred = clf.predict(Y_train_tfidf)
    
    ############################## Display Results #######################
    print('Total Songs Trained:', X_train_tfidf.shape[0])
    print('Total Songs Validated:', Y_train_tfidf.shape[0])
    print("Best parameters set found on development set:",clf.best_params_)
    print('mean_train_score:',clf.cv_results_['mean_train_score'][clf.best_index_])
    print('mean_test_score:',clf.cv_results_['mean_test_score'][clf.best_index_])
    print('Best Score:', clf.best_score_ )
    print('Confusion matrix:')
    print(confusion_matrix(testLabels, y_pred))
    #########################################################################












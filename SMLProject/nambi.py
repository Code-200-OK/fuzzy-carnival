from sklearn.feature_extraction.text import CountVectorizer
from nltk.stem.snowball import SnowballStemmer
from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.svm import SVC
from sklearn.metrics import confusion_matrix
from sklearn.cross_validation import train_test_split
from sklearn.model_selection import GridSearchCV


from sklearn.neighbors.nearest_centroid import NearestCentroid
from sklearn.naive_bayes import GaussianNB
from sklearn.neural_network import MLPClassifier
from sklearn.discriminant_analysis import QuadraticDiscriminantAnalysis

from sklearn.gaussian_process import GaussianProcessClassifier
from sklearn.gaussian_process.kernels import RBF, ConstantKernel as C

from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import RandomForestClassifier


import os
import re
import time

# encoding='ascii',decode_error='ignore',strip_accents='unicode',
vectorizer = CountVectorizer(encoding='ascii',decode_error='ignore',strip_accents='unicode',input='filename', ngram_range=(1,1),stop_words='english',token_pattern="(?u)\\b[A-Za-z][A-Za-z][A-Za-z]+\\b")
#print vectorizer
analyzer = vectorizer.build_analyzer()
stemmer = SnowballStemmer("english")
current_milli_time = lambda: int(round(time.time() * 1000))

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
    LOC = "/home/jatsakthi/DATA/"
    trainlocations = ["HAPPY","SAD"]

    FilesList,Labels = readFileNames(trainlocations)
    
    #################### SPLIT THE DATASET #####################
    
    trainFiles, testFiles, trainLabels, testLabels = train_test_split(
    FilesList, Labels, test_size=0.3, random_state=current_milli_time()%100)
    
    ###############################################################
    
    # ###########        CONVERT TRAINING FILES IT INTO BOW    ###################
    stem_vectorizer = CountVectorizer(analyzer=stemmed_words)
    #print stem_vectorizer
    
    X_train_tfidf = stem_vectorizer.fit_transform(trainFiles)
    #print train_tfidf.shape
    Y_train_tfidf = stem_vectorizer.transform(testFiles)
    
    ##################################################################
    
    #print stem_vectorizer.get_feature_names()
    #print stem_vectorizer.vocabulary_.get(u'have')
    
    # ###########        CONVERT IT INTO TF-IDF    ###################
    
    tfidf_transformer = TfidfTransformer()
    X_train_tfidf = tfidf_transformer.fit_transform(X_train_tfidf)
    
    Y_train_tfidf = tfidf_transformer.transform(Y_train_tfidf)
    
    print X_train_tfidf.shape
    print Y_train_tfidf.shape
    
    ##################################################################
    
    '''
    
    # Set the parameters by cross-validation
    tuned_parameters = [{'kernel': ['rbf'], 'gamma': [1e-2, 1e-3, 1e-4],'C': [1, 10, 100, 1000, 1000000]},
                        {'kernel': ['linear'], 'C': [1, 10, 100, 1000, 1000000]}]
    
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

'''










# Knn classifier
clf = NearestCentroid()
clf.fit(X_train_tfidf, trainLabels)
NearestCentroid(metric='euclidean', shrink_threshold=None)
ans=clf.predict(Y_train_tfidf)
print('Total Songs Trained:', X_train_tfidf.shape[0])
print('Total Songs Validated:', Y_train_tfidf.shape[0])
classify=0
for i in range(0,len(ans)):
    if(ans[i]==testLabels[i]):
        classify=classify+1
print "KNN Classifier"
print 100*float(classify)/len(ans)

#print("Best parameters set found on development set:",clf.best_params_)
#print('mean_train_score:',clf.cv_results_['mean_train_score'][clf.best_index_])
#print('mean_test_score:',clf.cv_results_['mean_test_score'][clf.best_index_])
#print('Best Score:', clf.best_score_ )
#print('Confusion matrix:')
#print(confusion_matrix(testLabels, y_pred))
            
# Guassian Naive Bayes Classifier

clf = GaussianNB()
clf.fit(X_train_tfidf.toarray(), trainLabels)
GaussianNB(priors=None)
ans=clf.predict(Y_train_tfidf.toarray())
print('Total Songs Trained:', X_train_tfidf.shape[0])
print('Total Songs Validated:', Y_train_tfidf.shape[0])
classify=0
for i in range(0,len(ans)):
    if(ans[i]==testLabels[i]):
        classify=classify+1
print "Gaussian NB Classifier"
print 100*float(classify)/len(ans)

# Random Forest Classifier ->

clf = RandomForestClassifier(max_depth = 4)
clf.fit(X_train_tfidf, trainLabels)
ans=clf.predict(Y_train_tfidf)
print('Total Songs Trained:', X_train_tfidf.shape[0])
print('Total Songs Validated:', Y_train_tfidf.shape[0])
classify=0
for i in range(0,len(ans)):
    if(ans[i]==testLabels[i]):
        classify=classify+1
print "Random Forest Classifier"
print 100*float(classify)/len(ans)
            


# Decision Classifier -> 94.3563728599

clf = DecisionTreeClassifier(random_state=10)
clf.fit(X_train_tfidf, trainLabels)
ans=clf.predict(Y_train_tfidf)
print('Total Songs Trained:', X_train_tfidf.shape[0])
print('Total Songs Validated:', Y_train_tfidf.shape[0])
classify=0
for i in range(0,len(ans)):
    if(ans[i]==testLabels[i]):
        classify=classify+1
print "Decision Tree Classifier"
print 100*float(classify)/len(ans)
            
# Quadratic Discriminant Analysis Classifier -> 94.0393151554

clf = QuadraticDiscriminantAnalysis()
clf.fit(X_train_tfidf.toarray(), trainLabels)
QuadraticDiscriminantAnalysis(priors=None, reg_param=0.0,store_covariances=False, tol=0.0001)
ans=clf.predict(Y_train_tfidf.toarray())
print('Total Songs Trained:', X_train_tfidf.shape[0])
print('Total Songs Validated:', Y_train_tfidf.shape[0])
classify=0
for i in range(0,len(ans)):
    if(ans[i]==testLabels[i]):
        classify=classify+1
print "Quadratic Discriminant Analysis Classifier"
print 100*float(classify)/len(ans)

# Neural Network MLP Classifier   ->  94.1661382372

clf = MLPClassifier(hidden_layer_sizes=(10),solver='sgd',learning_rate_init=0.01,max_iter=500)
clf.fit(X_train_tfidf, trainLabels)

sl = 5.8
sw = 4
pl = 1.2
pw = 0.2
data = [sl,sw,pl,pw]
ans=clf.predict(Y_train_tfidf)
print('Total Songs Trained:', X_train_tfidf.shape[0])
print('Total Songs Validated:', Y_train_tfidf.shape[0])
classify=0
for i in range(0,len(ans)):
    if(ans[i]==testLabels[i]):
        classify=classify+1
print "Neural Network MLP Classifier"
print 100*float(classify)/len(ans)
         
# print classify,total
# print 100*float(classify)/total

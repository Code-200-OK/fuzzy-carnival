import os
import numpy
from sklearn.feature_extraction.text import CountVectorizer
from pandas import DataFrame
from sklearn.naive_bayes import MultinomialNB
from io import open
from sklearn.cross_validation import KFold
from sklearn.metrics import confusion_matrix, f1_score
from sklearn.pipeline import Pipeline
from sklearn.svm import SVC
from sklearn.feature_extraction.text import TfidfVectorizer


NEWLINE = '\n'
SKIP_FILES = {'cmds'}


def read_files(path):
    for root, dir_names, file_names in os.walk(path):
        for path in dir_names:
            read_files(os.path.join(root, path))
        for file_name in file_names:
            if file_name not in SKIP_FILES:
                file_path = os.path.join(root, file_name)
                if os.path.isfile(file_path):
                    past_header, lines = False, []
                    f = open(file_path, "rt", encoding="latin-1")
                    for line in f:
                        if past_header:
                            lines.append(line)
                        elif line == NEWLINE:
                            past_header = True
                    f.close()
                    content = NEWLINE.join(lines)
                    yield file_path, content
                    


def build_data_frame(path, classification):
    rows = []
    index = []
    for file_name, text in read_files(path):
        rows.append({'text': text, 'class': classification})
        index.append(file_name)
    
    data_frame = DataFrame(rows, index=index)
    return data_frame

def train_svm(X, y):
    """
    Create and train the Support Vector Machine.
    """
    svm = SVC(C=1000000.0, gamma='auto', kernel='rbf')
    svm.fit(X, y)
    return svm

def create_tfidf_training_data(corpus):
    """
    Creates a document corpus list (by stripping out the
    class labels), then applies the TF-IDF transform to this
    list. 

    The function returns both the class label vector (y) and 
    the corpus token/feature matrix (X).
    """

    # Create the TF-IDF vectoriser and transform the corpus
    vectorizer = TfidfVectorizer(min_df=1)
    X = vectorizer.fit_transform(corpus)
    return X

if __name__ == "__main__":
    HAM = 0
    SPAM = 1

    SOURCES = [
        ("/home/jatsakthi/SML/classification/Document Classification/Spam-Ham/Dataset/Spam",        SPAM),
        ("/home/jatsakthi/SML/classification/Document Classification/Spam-Ham/Dataset/Ham",        HAM),
        
    ]
    #SOURCES = [
    #    ("/home/jatsakthi/SML/classification/Data/Spam",        SPAM),
    #    ("/home/jatsakthi/SML/classification/Data/Ham",        HAM),
        
    #]
    data = DataFrame({'text': [], 'class': []})
    for path, classification in SOURCES:
        data = data.append(build_data_frame(path, classification))
        
    data = data.reindex(numpy.random.permutation(data.index))
    
    tot = create_tfidf_training_data(data['text'].values)
    
    k_fold = KFold(n=len(data), n_folds=6)
    scores = []
    confusion = numpy.array([[0, 0], [0, 0]])
    for train_indices, test_indices in k_fold:
        train_text = tot[train_indices]
        train_y = data.iloc[train_indices]['class'].values
    
        test_text = tot[test_indices]
        test_y = data.iloc[test_indices]['class'].values
        
        svm = train_svm(train_text, train_y)
        
        
        score = svm.score(test_text, test_y)
        pred = svm.predict(test_text)
        confusion_matrix(pred, test_y)
        scores.append(score)
        print score

    print('Total emails classified:', len(data))
    print('Score:', sum(scores)/len(scores))
    

    
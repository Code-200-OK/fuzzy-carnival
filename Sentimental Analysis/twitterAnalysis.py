#import regex
import re
import csv
import nltk
from sklearn.svm import LinearSVC

#start process_tweet
def processTweet(tweet):
    # process the tweets

    #Convert to lower case
    tweet = tweet.lower()
    #Convert www.* or https?://* to URL
    tweet = re.sub('((www\.[^\s]+)|(https?://[^\s]+))','URL',tweet)
    #Convert @username to AT_USER
    tweet = re.sub('@[^\s]+','AT_USER',tweet)
    #Remove additional white spaces
    tweet = re.sub('[\s]+', ' ', tweet)
    #Replace #word with word
    tweet = re.sub(r'#([^\s]+)', r'\1', tweet)
    #trim
    tweet = tweet.strip('\'"')
    return tweet
#end

#start getStopWordList
def getStopWordList(stopWordListFileName):
    #read the stopwords file and build a list
    stopWords = []
    stopWords.append('AT_USER')
    stopWords.append('URL')

    fp = open(stopWordListFileName, 'r')
    line = fp.readline()
    while line:
        word = line.strip()
        stopWords.append(word)
        line = fp.readline()
    fp.close()
    return stopWords

#start replaceTwoOrMore
def replaceTwoOrMore(s):
    #look for 2 or more repetitions of character and replace with the character itself
    pattern = re.compile(r"(.)\1{1,}", re.DOTALL)
    return pattern.sub(r"\1\1", s)
#end

#start getfeatureVector
def getFeatureVector(tweet,stopWords):
    featureVector = []
    #split tweet into words
    words = tweet.split()
    for w in words:
        #replace two or more with two occurrences
        w = replaceTwoOrMore(w)
        #strip punctuation
        w = w.strip('\'"?,.')
        #check if the word stats with an alphabet
        val = re.search(r"^[a-zA-Z][a-zA-Z0-9]*$", w)
        #ignore if it is a stop word
        if(w in stopWords or val is None):
            continue
        else:
            featureVector.append(w.lower())
    return featureVector
#end

#start extract_features
def extract_features(tweet):
    tweet_words = set(tweet)
    features = {}
    for word in featureList:
        features[word] = (word in tweet_words)
    return features
#end


'''
LOC = "/home/jatsakthi/SML/classification/Document Classification/Tweet/"

#Read the stop words list and add them to a list
st = open(LOC+'data/stopwords.txt', 'r')


#Read the tweets one by one and process it
fp = open(LOC+'/data/sampleTweets.txt', 'r')
line = fp.readline()

while line:
    processedTweet = processTweet(line)
    featureVector = getFeatureVector(processedTweet)
    print featureVector
    line = fp.readline()
#end loop
fp.close()
'''

LOC = "/home/jatsakthi/SML/classification/Document Classification/Tweet/"
stopWords = getStopWordList(LOC+'data/stopwords.txt')
featureList = []
inpTweets = csv.reader(open(LOC+'data/sampleTweets.csv', 'rb'), delimiter=',', quotechar='|')
tweets = []
for row in inpTweets:
    sentiment = row[0]
    tweet = row[1]
    processedTweet = processTweet(tweet)
    featureVector = getFeatureVector(processedTweet, stopWords)
    featureList.extend(featureVector)
    tweets.append((featureVector, sentiment));
#end loop

# Remove featureList duplicates
featureList = list(set(featureList))


# Extract feature vector for all tweets in one shote
training_set = nltk.classify.util.apply_features(extract_features, tweets)

print training_set
# Train the classifier
NBClassifier = nltk.NaiveBayesClassifier.train(training_set)

#classifier = nltk.classify.SklearnClassifier(LinearSVC())
#classifier.train(training_set)


#print classifier.show_most_informative_features(10)

# Test the classifier
testTweet = 'Good night #Twitter and #TheLegionoftheFallen.  5:45am cimes awfully early!'
processedTestTweet = processTweet(testTweet)
print NBClassifier.classify(extract_features(getFeatureVector(processedTestTweet,stopWords)))


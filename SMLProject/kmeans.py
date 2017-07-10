
from sklearn.cluster import KMeans
import matplotlib.pyplot as plt

def doData():
    f = open(LOC, "rt")
    lines = []
    for line in f:
        lines.append(line.split("\t")[:7])
    f.close()
    return lines;
def distance(a,b):
    total = 0
    for i in range(0,len(a)):
        total += ((float(a[i]) - float(b[i]))**2)
    return total
def compute(X,clusters,pointClusters):
    total = 0
    for i in range(0,len(X)):
        total += distance(X[i],clusters[pointClusters[i]])
    return total
if __name__=="__main__":
    LOC = "/home/jatsakthi/SML/Assignment 3/a3-data/seeds.txt"
    X = doData()
    plotx = []
    ploty = []
    for k in range(2,11):
        kmeans = KMeans(n_clusters=k, random_state=0).fit(X)
        pointClusters = kmeans.labels_
        clusters = kmeans.cluster_centers_
        plotx.append(k)
        ploty.append(compute(X,clusters,pointClusters))
    plt.plot(plotx,ploty,'k',label="value")
    plt.xlabel("Number of Clusters: K")
    plt.ylabel("Objective Function Value")
    plt.legend()
    plt.show()
    
    
import pandas as pd

def build_data_frame(path):
    path += "/valid_lyrics_200.csv"
    df = pd.read_csv(path,usecols=['file','lyrics','mood'])
    return df

def writeLyrics(path, title, song,mood):
    song = song.decode('ascii', 'ignore')
    file = open(path+"/"+mood+"/"+title+".txt","w")
    file.write(song)
    file.close()

if __name__ == "__main__":
    LOC = "/home/jatsakthi/SML/classification/Document Classification/Songs/Dataset/MSD"
    data = build_data_frame(LOC)
    for row in data.values:
        writeLyrics(LOC, row[0], row[1],row[2])
    
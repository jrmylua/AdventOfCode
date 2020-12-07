import re

def main():
    f = open("input.txt")
    data = f.readlines()
    dict = {}
    for line in data:
        split = line.split(" contain ")
        mainBag = " ".join(split[0].split()[:2])
        rules = split[1].split(',')
        rules = [item.strip() for item in rules]
        rules = [re.sub(" bags?\.?", "", item) for item in rules]
        containsBags = [" ".join(item.split()[-2:]) for item in rules]
        dict[mainBag] = set(containsBags)

    myResultSet = set()
    getCount(myResultSet, "shiny gold", dict)
    print (len(myResultSet))

def getCount(resultSet, bagName, dict):
    newSet = set()
    for outerBag, innerBags in dict.items():
        if bagName in innerBags:
            resultSet.add(outerBag)
            newSet.add(outerBag)
    for key in newSet:
        dict.pop(key)
    for key in newSet:
        getCount(resultSet, key, dict)

if __name__ == "__main__":
    main()
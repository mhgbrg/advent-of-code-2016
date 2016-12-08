import re
import sys

def main():
    inputs = sys.stdin.read().splitlines()

    valids = list(filter(lambda ip: supportsSSL(ip), inputs))

    print(len(valids))


def supportsSSL(ip):
    insideBrackets = False

    for i in range(0, len(ip) - 2):
        if ip[i] == '[':
            insideBrackets = True
        elif ip[i] == ']':
            insideBrackets = False
        elif not insideBrackets and ip[i] == ip[i + 2]:
            if existsInBrackets(ip, ip[i + 1] + ip[i] + ip[i + 1]):
                return True

    return False


def existsInBrackets(str, substring):
    insideBrackets = False

    for i in range(0, len(str) - 2):
        if str[i] == '[':
            insideBrackets = True
        elif str[i] == ']':
            insideBrackets = False
        elif insideBrackets and str[i] + str[i + 1] + str[i + 2] == substring:
            return True

    return False


if __name__ == '__main__':
    main()

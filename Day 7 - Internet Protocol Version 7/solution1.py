import re
import sys

def main():
    inputs = sys.stdin.read().splitlines()

    doNotMatch = r'\[[^\]]*?(.)(?!\1)(.)\2\1.*?\]'
    match = r'(.)(?!\1)(.)\2\1'

    supportsTls = list(filter(lambda str: not re.search(doNotMatch, str) and re.search(match, str), inputs))

    print(len(supportsTls))


if __name__ == '__main__':
    main()

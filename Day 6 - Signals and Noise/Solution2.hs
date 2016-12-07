import Data.List

main = do
  input <- readFile "input.txt"
  let solution = solve $ lines input
  putStrLn solution

solve :: [String] -> String
solve lines = map mostFrequentChar columns
  where columns = transpose lines

mostFrequentChar :: String -> Char
mostFrequentChar str = c
  where counts = charCounts str
        (c, _) = maximumBy compareCounts counts
        compareCounts (_, n1) (_, n2) = n2 `compare` n1

charCounts :: String -> [(Char, Int)]
charCounts str = map (\c -> (c, countOccurrences c str)) uniqueChars
  where uniqueChars = nub str

countOccurrences :: Char -> String -> Int
countOccurrences c = length . filter (== c)

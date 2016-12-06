import Data.Maybe
import Data.Char
import Data.List
import Data.Ord
import Data.String.Utils
import Data.Digest.Pure.MD5
import qualified Data.ByteString as B
import qualified Data.ByteString.Char8 as C
import qualified Data.ByteString.Lazy as BLazy
import qualified Data.ByteString.Lazy.Char8 as CLazy

getPassword :: String -> Int -> String
getPassword str n = map (!! 6) sortedHashes
  where hashes = firstNValidHashes str n
        sortedHashes = sortBy compareHashes hashes

compareHashes :: String -> String -> Ordering
compareHashes h1 h2 | (h1 !! 5) < (h2 !! 5) = LT
                    | otherwise = GT

firstNValidHashes :: String -> Int -> [String]
firstNValidHashes str n = firstNValidHashes' str 0 [0 .. n - 1]

firstNValidHashes' :: String -> Int -> [Int] -> [String]
firstNValidHashes' _ _ [] = []
firstNValidHashes' str i positionsLeft | pos `elem` positionsLeft = hash : firstNValidHashes' str (i' + 1) positionsLeft'
                                       | otherwise = firstNValidHashes' str (i' + 1) positionsLeft
  where (i', hash) = firstValidHash str i
        pos = charToInt $ hash !! 5
        positionsLeft' = delete pos positionsLeft

charToInt :: Char -> Int
charToInt c = ord c - ord '0'

firstValidHash :: String -> Int -> (Int, String)
firstValidHash str i | startswith "00000" hash && (hash !! 5) <= '7' = (i, hash)
                     | otherwise = firstValidHash str (i + 1)
  where hash = md5' (str ++ show i)

md5' :: String -> String
md5' str = show . md5 $ CLazy.pack str

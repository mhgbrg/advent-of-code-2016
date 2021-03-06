import Data.Char
import Data.String.Utils
import Data.Digest.Pure.MD5
import qualified Data.ByteString.Lazy.Char8 as CLazy

getPassword :: String -> Int -> String
getPassword str n = map (!! 5) $ firstNValidHashes str n

firstNValidHashes :: String -> Int -> [String]
firstNValidHashes str n = firstNValidHashes' str 0 n

firstNValidHashes' :: String -> Int -> Int -> [String]
firstNValidHashes' _ _ 0 = []
firstNValidHashes' str i n = hash : firstNValidHashes' str (i' + 1) (n - 1)
  where (i', hash) = firstValidHash str i

firstValidHash :: String -> Int -> (Int, String)
firstValidHash str i | startswith "00000" hash = (i, hash)
                              | otherwise = firstValidHash str (i + 1)
  where hash = md5' (str ++ show i)

md5' :: String -> String
md5' str = show . md5 $ CLazy.pack str

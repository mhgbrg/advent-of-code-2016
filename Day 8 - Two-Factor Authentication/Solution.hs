import Data.Char
import Data.List
import Data.String.Utils

main = do
  input <- readFile "input.txt"
  let fns = map parseInstruction $ lines input
  let screen = foldl (\screen fn -> fn screen) blankScreen fns
  let n = countOns screen
  putStrLn . show $ n
  -- printScreen screen

parseInstruction :: String -> (Screen -> Screen)
parseInstruction instr = fn
  where (action:params) = words instr
        fn = case action of "rect" -> let (x:y:_) = params in rect (read x) (read y)
                            "rotateCol" -> let (x:by:_) = params in rotateCol (read x) (read by)
                            "rotateRow" -> let (y:by:_) = params in rotateRow (read y) (read by)

data Pixel = On | Off
  deriving(Eq, Show)

type Screen = [[Pixel]]

countOns :: Screen -> Int
countOns rows = length . filter (== On) $ pixels
  where pixels = concat rows

turnOnPixel :: Screen -> (Int, Int) -> Screen
turnOnPixel screen pos = update screen pos On

turnOffPixel :: Screen -> (Int, Int) -> Screen
turnOffPixel screen pos = update screen pos Off

update :: Screen -> (Int, Int) -> Pixel -> Screen
update rows (x, y) pixel = rows !!= (y, updatedRow)
  where row = rows !! y
        updatedRow = row !!= (x, pixel)

(!!=) :: [a] -> (Int, a) -> [a]
xs !!= (i, value) = init ++ [value] ++ tail
  where (init, xi:tail) = splitAt i xs

printScreen :: Screen -> IO ()
printScreen rows = mapM_ putStrLn $ map rowToString rows
  where rowToString = map pixelToChar

pixelToChar :: Pixel -> Char
pixelToChar pixel = case pixel of On -> '#'
                                  Off -> ' '

blankScreen :: Screen
blankScreen = replicate 6 . replicate 50 $ Off

rect :: Int -> Int -> Screen -> Screen
rect a b screen = foldl turnOnPixel screen pos
  where pos = [ (x, y) | x <- [0 .. a - 1], y <- [0 .. b - 1] ]

rotateCol :: Int -> Int -> [[a]] -> [[a]]
rotateCol x by rows = transpose $ rotateRow x by cols
  where cols = transpose rows

rotateRow :: Int -> Int -> [[a]] -> [[a]]
rotateRow y by rows = rows !!= (y, rotatedRow)
  where row = rows !! y
        rotatedRow = rotateN row by

rotate :: [a] -> [a]
rotate xs = [last xs] ++ (init xs)

rotateN :: [a] -> Int -> [a]
rotateN xs n = iterate rotate xs !! n

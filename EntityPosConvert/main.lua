--Must be edited to be used, intended use is bulk updating coordinates from SAB lvl files when scaling is changed

function roundTo2Decimal(num)
    return math.floor(num * 100) / 100 --move the first two decimal places to safety, floor, then move them back to being decimal places
end

for line in io.lines("input.txt") do
    wordTable = {}
    outputTable = {}

    for w in line:gmatch("%S+") do table.insert(wordTable, w) end
    
    for i = 1, #wordTable do
        if i == 1 then
            table.insert(outputTable, "96")
            --table.insert(outputTable, "4")
        elseif i == 2 then --x
            table.insert(outputTable, roundTo2Decimal(wordTable[i] / 1.25))
        elseif i == 3 then --y
            table.insert(outputTable, roundTo2Decimal((wordTable[i] - 409.7) / 1.25 - 1.3))
        elseif i == 4 then --z
            table.insert(outputTable, roundTo2Decimal(wordTable[i] / 1.25))
        --[[elseif i == 5 then --yrot
            table.insert(outputTable, wordTable[i])
        ]]
        elseif i > 4 then
            table.insert(outputTable, wordTable[i])
        end --if elseif elseif
    end --for

    for _, word in ipairs(outputTable) do
        io.write(word .. " ")
    end --for
    io.write("\n\r")
    
end --for
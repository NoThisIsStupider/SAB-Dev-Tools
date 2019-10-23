--commented versions are hardcoded filenames

function countTabs(str)
    local count = 0;
    for i = 1, string.len(str) do
        if string.sub(str, i, i) == "\t" then
            count = count + 1
        end --if
    end --for
    return count
end --countTabs

function getFilename(str)
	local words = {}
	for w in str:gmatch("([^/]*)") do table.insert(words, w) end
	return words[#words]
end --getFilename

print("Fixer started!")

skipOne = false
for _, argument in pairs(arg) do
	if (argument == "lua" or argument == "main.lua") then
		break
	end --if
	
	print("./output/" .. getFilename(argument))

	io.output("./output/" .. getFilename(argument), "w")
	--io.output("new.mtl", "w")

	for line in io.lines(argument) do
	--for line in io.lines("old.mtl") do
		local tabTotal = countTabs(line) + 1 --lua arrays start at one
		if string.sub(line, tabTotal, tabTotal - 1 + 3) == "new" then
			io.write(string.sub(line, tabTotal, string.len(line)) .. "\n")
		elseif string.sub(line, tabTotal, tabTotal - 1 + 2) == "Ns" then
			io.write("\tNs 20\n")
		elseif string.sub(line, tabTotal, tabTotal - 1 + 2) == "Ni" then
			io.write("\tNi 0.02\n")
		elseif tabTotal == 1 then --because of the +1 earlier
			io.write("\t" .. string.sub(line, tabTotal, string.len(line)) .. "\n")
		else
			io.write(line .. "\n")
		end --if
	end --for
end --for

print("Fixer finished!")

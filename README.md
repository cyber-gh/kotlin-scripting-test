<h3>Generates Kotlin data classes from json string</h3>
Uses Gson for parsing JSON<br>
Examples usage: <br> 
<code>val generator = TypeGenerator(workingDirectory)\n
generator.outputFlatType(jsonString)
</code>
<br>
This will output the generated kt file in the working directory <br>
Does not work with nested json objects

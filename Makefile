default:
	javac -sourcepath src -d bin `find src -name "*.java"`
	cd bin; jar cfve tp2.jar br.com.lealdn.tp2.Start `find .`
	@mv bin/tp2.jar .
	@echo "================================"
	@echo "Arquivo: tp2.jar criado"
	@echo "Ajuda        : java -jar tp2.jar"
	@echo "Para executar: java -jar tp2.jar <input-file> <output-file> <algoritmo>"
	@echo "1 - Dynamic, 2 - Greedy 3 - Backtracking, "
clean:
	rm -Rf bin;
	mkdir bin;


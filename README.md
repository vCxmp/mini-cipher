# mini-cipher
This encodes words or phrases using various ciphers tactics.

The ciphers tactics are CaeserShift, CaeserKey, Substitution, and multiple ciphers at once. 

SUBSTITUTIONS EXPLAINED BELOW:

CaeserShift - each letter in the plaintext is shifted by a fixed number of places in the encodeable range of characters

CaeserKey - places a provided character key at the front of substitution and then the remaining characters of the encodeable range (minus the charcters used in the key) are placed in the back

Substitution - plainly substites characters in a word with the corresponding character of the same index in a provided encoding character sequence

MultiCipher: uses all three ciphers at once to encode a word or phrase


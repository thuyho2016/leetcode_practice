package string;
/*
 * Telephone directory application with insert and search mechanism, data in app memory.
First name(FN), last name(LN) and telephone number(TN) will be inserted.
Can be searched by either first name, last name combo, or last name.
Constraint on data is FN, LN combination will be unique.

Example:-
F1 L1 T1
F2 L1 T2
F1 L2 T3
F2 L2 T4

Ask what whould be best Data Structure to build it, and assume that it has Ms of data.

Solution:

we can have first-level hash table keyed by last-name and its value can be another hash table (second-level). 
	HashMap<String, HashMap> map1 = new HashMap<>();   {"Tran" -> map2}
The second-level hash table would be keyed by first name and its value would the phone number. 
    HashMap<String, String> map2 = new HashMap<>();   {"Kathy"-> "408-123-4567"}

If lookup is done on lastname, we do lookup on the first-level hash table and access the (second-level) hash-table and have all the values. 
If the lookup is done on lastname+firstname, then we first do a lookup on the first-level hash table, followed by lookup on the second-level hash table to find the phone number

 */
public class TelephoneDirectory {

}

import pexpect
 

def test_23_general():
    baza = pexpect.pexpect()

    try:
        baza.expect("Enter command: ")

        baza.send("use 23")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("add 12345678 Jovan Prodanov 21 pfiezer")
        baza.expect("OK")
        baza.expect("Enter command: ")
 
        baza.send("add 12345678 Janez Levak 18 astra-zeneka")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("add 12345678 Andrej Brodnik 18 astra-zeneka")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("print")
        baza.expect("12345678 | Janez | Levak | 18 | astra-zeneka")
        baza.expect("12345678 | Andrej | Brodnik | 18 | astra-zeneka")
        baza.expect("12345678 | Jovan | Prodanov | 21 | pfiezer")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("count")
        baza.expect("3")
        baza.expect("Enter command: ")

        baza.send("depth")
        baza.expect("2")
        baza.expect("Enter command: ")

        baza.send("getfirst")
        baza.expect("12345678 | Janez | Levak | 18 | astra-zeneka")
        baza.expect("Enter command: ")

        print "PASSED\ttest_23_general"

    except:
        print "FAILED\ttest_23_general"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_23_general()


import pexpect
 

def test_exit_no_use():
    baza = pexpect.pexpect()

    try:
        baza.expect("Enter command: ")

        baza.send("exit")
        baza.expect("Have a nice day.")
        print "PASSED\ttest_exit_no_use"

    except:
        print "FAILED\ttest_exit_no_use"

    finally:
        baza.kill()

 
def test_exit_use_23():
    baza = pexpect.pexpect()

    try:
        baza.expect("Enter command: ")

        baza.send("use 23")
        baza.expect("OK")
        baza.expect("Enter command: ")

        baza.send("exit")
        baza.expect("Have a nice day.")
        print "PASSED\ttest_exit_use_23"

    except:
        print "FAILED\ttest_exit_use_23"

    finally:
        baza.kill()


if __name__ == "__main__":
    test_exit_no_use()
    test_exit_use_23()


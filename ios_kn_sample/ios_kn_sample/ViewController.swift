import UIKit
import lib

class ViewController: UIViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        DispatchQueue.global().async {
            Greeting().start()
        }
    }
}

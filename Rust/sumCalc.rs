trait Let {
    fn let_imut<R, F>(&self, block: F) -> R where F: FnOnce(&Self) -> R;
}

impl <T> Let for T {
    fn let_imut<R, F>(&self, block: F) -> R where F: FnOnce(&Self) -> R {
        block(self)
    }
}

fn main() {
    loop {
        let mut s = String::new();
        std::io::stdin().read_line(&mut s).unwrap();
        s.split_whitespace()
            .filter_map(|c| c.parse::<f64>().ok())
            .sum::<f64>()
            .let_imut(|n| println!("{}", n))
    }
}

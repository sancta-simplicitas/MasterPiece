use std::io::stdin;

trait KtStd {
    fn let_ref<R, F>(&self, block: F) -> R where F: FnOnce(&Self) -> R;

    fn also_mut<F>(&mut self, block: F) -> &mut Self where F: FnMut(&mut Self);
}

impl<T> KtStd for T {
    fn let_ref<R, F>(&self, block: F) -> R where F: FnOnce(&Self) -> R {
        block(self)
    }

    fn also_mut<F>(&mut self, mut block: F) -> &mut Self where F: FnMut(&mut Self) {
        block(self);
        self
    }
}

fn main() {
    loop {
        String::new()
            .also_mut(|mut s| { stdin().read_line(&mut s).unwrap(); })
            .split_whitespace()
            .filter_map(|c| c.parse::<f64>().ok())
            .sum::<f64>()
            .let_ref(|n| println!("{}", n))
    }
}

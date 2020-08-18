use std::io::stdin;

trait KtStd {
    fn let_ref<R>(&self, block: fn(&Self) -> R) -> R {
        block(self)
    }

    fn also_mut(&mut self, block: fn(&mut Self)) -> &mut Self {
        block(self);
        self
    }
}

impl<T> KtStd for T {}

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

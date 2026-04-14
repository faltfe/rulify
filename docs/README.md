```bash
docker run --rm -v $(pwd -W):/srv/jekyll jekyll/jekyll bundle install
``` 

```bash
docker run --rm -v $(pwd -W):/srv/jekyll --publish '[::1]:4000:4000' jekyll/jekyll jekyll serve
```

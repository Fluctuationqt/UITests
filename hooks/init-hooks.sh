# 1. Setup pre-commit to be executable
chmod 755 ./hooks/pre-commit

# 2. Symlink custom hooks folder (from public repo) into local .git/hooks folder 
find .git/hooks -type l -exec rm {} \; && find hooks -type f -exec ln -sf ../../{} .git/hooks/ \;